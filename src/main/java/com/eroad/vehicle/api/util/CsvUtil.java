package com.eroad.vehicle.api.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.eroad.vehicle.api.dto.VehicleData;
import com.eroad.vehicle.api.exception.VehicleApiException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

@Component
public class CsvUtil {
    
    public List<VehicleData> parse(MultipartFile file, String csvEncoding) throws VehicleApiException {
        try (Reader reader = new InputStreamReader(file.getInputStream(), csvEncoding);
                CSVReader csvReader = new CSVReader(reader)) {
            ColumnPositionMappingStrategy<VehicleData> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(VehicleData.class);
            String[] columns = new String[] { "utc_datetime", "latitude", "longitude" };
            strategy.setColumnMapping(columns);
            
            CsvToBean<VehicleData> csvToBean = new CsvToBean<>();
            return csvToBean.parse(strategy, reader);
        } catch (IOException ex) {
            throw new VehicleApiException("csv parsing failed", ex);
        }
    }
    
    public CSVWriter writer(OutputStreamWriter writer) {
        return (CSVWriter) new CSVWriterBuilder(writer).withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END).withQuoteChar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).build();
    }
}
