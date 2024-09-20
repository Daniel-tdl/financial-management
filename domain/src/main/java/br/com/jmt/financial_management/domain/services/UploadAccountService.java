package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import br.com.jmt.financial_management.domain.exceptions.UploadFileException;
import br.com.jmt.financial_management.domain.ports.in.UploadAccountUseCase;
import br.com.jmt.financial_management.domain.ports.out.AccountPort;
import br.com.jmt.financial_management.domain.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadAccountService implements UploadAccountUseCase {

    private final AccountPort accountPort;

    @Override
    public void execute(MultipartFile multipartFile) {
        try {
            var file = getFile(multipartFile);
            if (Objects.isNull(file)) throw new UploadFileException("Erro ao importar o arquivo.");
            List<AccountEntity> entities = getAccounts(file);
            if (!entities.isEmpty()) accountPort.saveAll(entities);
        } catch (IOException e) {
            throw new UploadFileException("Erro ao importar o arquivo.");
        }
    }

    private List<AccountEntity> getAccounts(File file) throws IOException {
        Reader in = new FileReader(file);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);
        int aux = 0;
        List<AccountEntity> entities = new ArrayList<>();
        for (CSVRecord record : records) {
            if (aux > 0) {
                var entity = new AccountEntity();
                entity.setAmount(Double.parseDouble(record.get(0)));
                entity.setDatePayment(DateUtils.convertToLocalDateTime(record.get(1)));
                entity.setDateVenc(DateUtils.convertToLocalDateTime(record.get(2)));
                entity.setDescription(record.get(3));
                entity.setStatus(AccountStatusEnum.valueOf(record.get(4)));
                entities.add(entity);
            }
            aux++;
        }

        return entities;
    }

    private static File getFile(MultipartFile multipartFile) throws IOException {

        if (Objects.isNull(multipartFile) || multipartFile.isEmpty()) return null;
        var fileName = Objects.nonNull(multipartFile.getOriginalFilename()) ? multipartFile.getOriginalFilename() : "temp.csv";
        File file = new File(FileUtils.getTempDirectoryPath(), fileName);
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        return file;
    }
}