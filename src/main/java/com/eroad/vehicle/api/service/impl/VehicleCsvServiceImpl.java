package com.eroad.vehicle.api.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eroad.vehicle.api.dto.VehicleData;
import com.eroad.vehicle.api.exception.VehicleApiException;
import com.eroad.vehicle.api.exception.VehicleApiValidationException;
import com.eroad.vehicle.api.service.VehicleCsvService;
import com.eroad.vehicle.api.util.CsvProcessor;
import com.eroad.vehicle.api.util.CsvProcessorFactory;
import com.eroad.vehicle.api.util.CsvUtil;
import com.opencsv.CSVWriter;

/**
 * Impl class for {@link VehicleCsvService}
 * 
 * @author shenoy.adith
 *
 */
@Service
public class VehicleCsvServiceImpl implements VehicleCsvService {
    
    @Value("${csv.file.encoding:UTF-8}")
    private String csvFileEncoding;
    
    @Autowired
    private CsvUtil csvUtil;
    
    @Autowired
    private CsvProcessorFactory processorFactory;
    
    /**
     * {@inheritDoc}
     */
    public void localizeVehicleData(MultipartFile file, OutputStream outputStream) throws VehicleApiException {
        Path tempFilePath = null;
        try {
            tempFilePath = Files.createTempFile("vehicles", "csv");
            writeToTempFile(tempFilePath, file);
            // copy from temp file to response output stream
            IOUtils.copy(Files.newInputStream(tempFilePath), outputStream);
        } catch (IOException ex) {
            throw new VehicleApiException("error while writing to csv");
        } finally {
            deleteFile(tempFilePath);
        }
    }
    
    private void writeToTempFile(Path tempFile, MultipartFile file) throws IOException, VehicleApiException {
        List<VehicleData> vehicleInfo = csvUtil.parse(file, csvFileEncoding);
        
        CsvProcessor csvProcessor = processorFactory.constructNewProcessor();
        
        try (OutputStream fileStream = Files.newOutputStream(tempFile);
                CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(fileStream, csvFileEncoding),
                        CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)) {
            int lineNumber = 1;
            for (VehicleData vehicleData : vehicleInfo) {
                vehicleData.setLineNumber(lineNumber++);
                String[] csvLine = csvProcessor.process(vehicleData);
                if (Objects.nonNull(csvLine)) {
                    csvWriter.writeNext(csvLine);
                }
            }
        }
        
        if (!csvProcessor.getInvalidOperationList().isEmpty()) {
            throw new VehicleApiValidationException(csvProcessor.getInvalidOperationList());
        }
    }
    
    private void deleteFile(Path tempFilePath) {
        try {
            Files.deleteIfExists(tempFilePath);
        } catch (IOException e) {
        }
    }
}
