package com.johnllave.dentalclinic.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.PatientDocument;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.repository.PatientDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    private final AmazonS3 amazonS3;

    private final PatientService patientService;

    private final PatientMapper patientMapper;

    private final PatientDocumentRepository patientDocumentRepository;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.endPointUrl}")
    private String endPoint;

    @Autowired
    public AWSS3ServiceImpl(AmazonS3 amazonS3, PatientService patientService, PatientMapper patientMapper, PatientDocumentRepository patientDocumentRepository) {
        this.amazonS3 = amazonS3;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.patientDocumentRepository = patientDocumentRepository;
    }

    @Override
    @Async
    public void uploadFile(MultipartFile multipartFile, String patientId, String filename) {

        try {
            File file = convertMultipartFile(multipartFile);

            Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(patientId),
                    new CycleAvoidingMappingContext());

            String fileType = file.getName().substring(file.getName().lastIndexOf('.') + 1);

            String trimmedFilename = filename;

            if(filename.contains(".")) {
                trimmedFilename = filename.substring(0, filename.lastIndexOf('.'));

            }

            String uniqueFileName = patient.getUuid() + "/" + LocalDateTime.now() + "_" + trimmedFilename + "." + fileType;

            String FinalFilename = trimmedFilename + "." + fileType;


            PatientDocument patientDocument = new PatientDocument();

            patientDocument.setLink(this.endPoint + uniqueFileName);
            patientDocument.setFileName(FinalFilename);
            patientDocument.setDateUploaded(LocalDate.now());
            patientDocument.setFileType(fileType);

            patient.addDocument(patientDocument);

            patientService.savePatient(patientMapper.patientToPatientDto(patient, new CycleAvoidingMappingContext()));

            uploadFileToS3Bucket(bucketName, file, uniqueFileName);

            file.delete();
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void uploadFile(MultipartFile multipartFile, String patientId) {


        try {
            File file = convertMultipartFile(multipartFile);

            Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(patientId),
                    new CycleAvoidingMappingContext());

            String uniqueFileName = patient.getUuid() + "/" + "profile-image/" + LocalDateTime.now() + "_" +  file.getName();

            patient.setImage(this.endPoint + uniqueFileName);

            patientService.savePatient(patientMapper.patientToPatientDto(patient,
                    new CycleAvoidingMappingContext()));

            uploadFileToS3Bucket(bucketName, file, uniqueFileName);

            file.delete();


        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }

    }


    private void uploadFileToS3Bucket(String bucketName, File file, String uniqueFileName) {



        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file).withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(putObjectRequest);
    }

    @Override
    @Async
    public void deleteFileFromS3Bucket(String fileId) {


        PatientDocument patientDocument = patientDocumentRepository.findByUuid(fileId);

        String[] link = patientDocument.getLink().split("/");

        String fileName = link[link.length - 1];
        String folderName = link[link.length - 2];




        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, folderName + "/" + fileName));

        patientDocumentRepository.delete(patientDocument);
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
