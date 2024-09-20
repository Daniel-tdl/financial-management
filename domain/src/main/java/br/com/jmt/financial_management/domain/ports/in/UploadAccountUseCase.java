package br.com.jmt.financial_management.domain.ports.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface UploadAccountUseCase {
    void execute(MultipartFile multipartFile);
}
