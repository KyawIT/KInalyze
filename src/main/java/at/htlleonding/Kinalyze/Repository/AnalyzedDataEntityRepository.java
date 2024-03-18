package at.htlleonding.Kinalyze.Repository;

import at.htlleonding.Kinalyze.Entity.AnalyzedDataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalyzedDataEntityRepository extends CrudRepository<AnalyzedDataEntity, String> {

    @Query("SELECT a FROM AnalyzedDataEntity a")
    List<AnalyzedDataEntity> findAllAnalyzedData();

    @Query("SELECT a FROM AnalyzedDataEntity a WHERE a.user_uid = :id")
    AnalyzedDataEntity getAnalyzedDataEntityById(@Param("id") String id);
}
