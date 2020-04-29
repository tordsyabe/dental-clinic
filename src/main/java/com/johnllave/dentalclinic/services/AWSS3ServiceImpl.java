package com.johnllave.dentalclinic.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    private final AmazonS3 amazonS3;

    private final PatientService patientService;

    private final PatientMapper patientMapper;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.endPointUrl}")
    private String endPoint;

    @Autowired
    public AWSS3ServiceImpl(AmazonS3 amazonS3, PatientService patientService, PatientMapper patientMapper) {
        this.amazonS3 = amazonS3;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @Override
    @Async
    public void uploadFile(MultipartFile multipartFile, String patientId) {
        try {
            File file = convertMultipartFile(multipartFile);
            uploadFileToS3Bucket(bucketName, file, patientId);



            file.delete();
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }
    }

    private void uploadFileToS3Bucket(String bucketName, File file, String patientId) {

        String uniqueFileName = LocalDateTime.now() + "_" + file.getName().replace(" ", "_");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file).withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(putObjectRequest);

        Patient patient = patientService.getPatientById(Long.parseLong(patientId));

        patient.setImage(endPoint + uniqueFileName);

        patientService.savePatient(patientMapper.patientToPatientDto(patient));
    }


    private File convertMultipartFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
