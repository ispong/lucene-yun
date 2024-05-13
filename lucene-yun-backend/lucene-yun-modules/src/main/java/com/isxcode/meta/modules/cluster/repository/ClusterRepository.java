package com.isxcode.meta.modules.cluster.repository;

import com.isxcode.meta.api.main.constants.ModuleCode;
import com.isxcode.meta.modules.cluster.entity.ClusterEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = {ModuleCode.CLUSTER})
public interface ClusterRepository extends JpaRepository<ClusterEntity, String> {

    @Query("SELECT C FROM ClusterEntity C WHERE C.name LIKE %:searchKeyWord% OR C.remark LIKE %:searchKeyWord% order by C.createDateTime desc ")
    Page<ClusterEntity> pageCluster(@Param("searchKeyWord") String searchKeyWord, Pageable pageable);

    long countByStatus(String status);
}
