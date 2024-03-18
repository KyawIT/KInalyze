package at.htlleonding.Kinalyze.Controller;

import at.htlleonding.Kinalyze.Entity.AnalyzedDataEntity;
import at.htlleonding.Kinalyze.Entity.FileEntity;
import at.htlleonding.Kinalyze.Service.AnalyzeAll;
import at.htlleonding.Kinalyze.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileEntityService) {
        this.fileService = fileEntityService;
    }

    @PostMapping("/upload/file")
    public ResponseEntity<FileEntity> uploadFile(@RequestBody FileEntity requestBody) {
        System.out.println(requestBody.toString());
        fileService.saveFileEntity(requestBody);
        return ResponseEntity.ok(requestBody);
    }

    @PostMapping("/file/analyze")
    public ResponseEntity<AnalyzedDataEntity> analyzeFile(@RequestBody FileEntity requestBody) {
        System.out.println(requestBody.toString());
        AnalyzeAll analyzeAll = new AnalyzeAll(requestBody.getUser_uid(), requestBody.getFile_name(), requestBody.getFile_type(), requestBody.getFile_size(), requestBody.getFile_content());
        AnalyzedDataEntity analyzedData = analyzeAll.getAnalyzedCode();
        System.out.println(analyzedData.toString());
        fileService.saveAnalyzedDataEntity(analyzedData);
        return ResponseEntity.ok(analyzedData);
    }

    @GetMapping("/file/all")
    public ResponseEntity<List<FileEntity>> getAllFiles() {
        List<FileEntity> allFiles = fileService.getAllFiles();
        System.out.println(allFiles);
        if (!allFiles.isEmpty()) {
            return ResponseEntity.ok(allFiles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<FileEntity> getFileById(@PathVariable String id) {
        FileEntity file = fileService.getFileById(id);
        System.out.println(file);
        if (file != null) {
            return ResponseEntity.ok(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/analyzed/all")
    public ResponseEntity<List<AnalyzedDataEntity>> getAllAnalyzedData() {
        List<AnalyzedDataEntity> allAnalyzedData = fileService.getAllAnalyzedData();
        System.out.println(allAnalyzedData);
        if (!allAnalyzedData.isEmpty()) {
            return ResponseEntity.ok(allAnalyzedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/analyzed/{id}")
    public ResponseEntity<AnalyzedDataEntity> getAnalyzedById(@PathVariable String id) {
        AnalyzedDataEntity analyzedData = fileService.getAnalyzedDataId(id);
        System.out.println(analyzedData);
        if (analyzedData != null) {
            return ResponseEntity.ok(analyzedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
