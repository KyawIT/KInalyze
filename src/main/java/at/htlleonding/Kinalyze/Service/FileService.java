package at.htlleonding.Kinalyze.Service;

import at.htlleonding.Kinalyze.Entity.AnalyzedDataEntity;
import at.htlleonding.Kinalyze.Entity.FileEntity;
import at.htlleonding.Kinalyze.Repository.AnalyzedDataEntityRepository;
import at.htlleonding.Kinalyze.Repository.FileEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final FileEntityRepository fileEntityRepository;
    private final AnalyzedDataEntityRepository analyzedDataEntityRepository;

    public FileService(FileEntityRepository fileEntityRepository, AnalyzedDataEntityRepository analyzedDataEntityRepository) {
        this.fileEntityRepository = fileEntityRepository;
        this.analyzedDataEntityRepository = analyzedDataEntityRepository;
    }

    public List<FileEntity> getAllFiles() {
        return fileEntityRepository.findAllEntities();
    }

    public FileEntity getFileById(String id) {return fileEntityRepository.findFileEntityById(id);}

    public void saveFileEntity(FileEntity fileEntity) {
        fileEntityRepository.save(fileEntity);
    }
    public List<AnalyzedDataEntity> getAllAnalyzedData(){
        return analyzedDataEntityRepository.findAllAnalyzedData();
    }

    public AnalyzedDataEntity getAnalyzedDataId(String id){return analyzedDataEntityRepository.getAnalyzedDataEntityById(id);}
    public void saveAnalyzedDataEntity(AnalyzedDataEntity analyzedDataEntity){
        analyzedDataEntityRepository.save(analyzedDataEntity);
    }

}
