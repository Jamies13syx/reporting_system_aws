package com.antra.evaluation.reporting_system.repo;

import com.antra.evaluation.reporting_system.pojo.report.ExcelFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ExcelRepositoryImpl implements ExcelRepository {

//    Map<String, ExcelFile> excelData = new ConcurrentHashMap<>();
//
//
//    @Override
//    public Optional<ExcelFile> getFileById(String id) {
//        return Optional.ofNullable(excelData.get(id));
//    }
//
//    @Override
//    public ExcelFile saveFile(ExcelFile file) {
//        return excelData.put(file.getFileId(), file);
//    }
//
//    @Override
//    public ExcelFile deleteFile(String id) {
//        return excelData.remove(id);
//    }
//
//    @Override
//    public List<ExcelFile> getFiles() {
//        return new ArrayList<>(excelData.values());
//    }

    @Autowired
    RedisTemplate<String, ExcelFile> redisTemplate;
    @Override
    public Optional<ExcelFile> getFileById(String id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(id));
    }

    @Override
    public void saveFile(ExcelFile file) {
        redisTemplate.opsForValue().set(file.getFileId(), file);
    }

    @Override
    @Transactional
    public ExcelFile deleteFile(String id) {
        ExcelFile excelFile = redisTemplate.opsForValue().get(id);
        if (excelFile != null) {
            redisTemplate.delete(id);
        }
        return excelFile;
    }

    @Override
    public List<ExcelFile> getFiles() {
        Set<String> keys = redisTemplate.keys("*");
        List<ExcelFile> files = new ArrayList<>();
        if (keys != null) {
            for (String key : keys) {
                files.add(redisTemplate.opsForValue().get(key));
            }
        }
        return files;
    }
}

