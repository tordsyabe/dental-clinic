package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.entity.Patient;
import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

    void uploadFile(MultipartFile multipartFile, String patientId);

    void uploadFile(MultipartFile multipartFile, String patientId, String filename);

    void deleteFileFromS3Bucket(String fileId);
}
