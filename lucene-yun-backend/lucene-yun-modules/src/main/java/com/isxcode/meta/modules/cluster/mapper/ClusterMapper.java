package com.isxcode.acorn.modules.cluster.mapper;

import com.isxcode.acorn.api.cluster.constants.ClusterStatus;
import com.isxcode.acorn.api.cluster.pojos.req.AddClusterReq;
import com.isxcode.acorn.api.cluster.pojos.req.UpdateClusterReq;
import com.isxcode.acorn.api.cluster.pojos.res.PageClusterRes;
import com.isxcode.acorn.api.cluster.pojos.res.QueryAllClusterRes;
import com.isxcode.acorn.modules.cluster.entity.ClusterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * mapstruct映射.
 */
@Mapper(componentModel = "spring")
public interface ClusterMapper {

    @Mapping(target = "activeNodeNum", expression = "java(0)")
    @Mapping(target = "allNodeNum", expression = "java(0)")
    @Mapping(target = "usedMemoryNum", expression = "java(0.0)")
    @Mapping(target = "allMemoryNum", expression = "java(0.0)")
    @Mapping(target = "usedStorageNum", expression = "java(0.0)")
    @Mapping(target = "allStorageNum", expression = "java(0.0)")
    @Mapping(target = "checkDateTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", constant = ClusterStatus.NEW)
    ClusterEntity addEngineReqToClusterEntity(AddClusterReq addClusterReq);

    @Mapping(target = "name", source = "caeUpdateEngineReq.name")
    @Mapping(target = "remark", source = "caeUpdateEngineReq.remark")
    ClusterEntity updateEngineReqToClusterEntity(UpdateClusterReq caeUpdateEngineReq,
        ClusterEntity calculateEngineEntity);

    @Mapping(target = "node",
        expression = "java( clusterEntity.getActiveNodeNum()+ \"/\" +clusterEntity.getAllNodeNum())")
    @Mapping(target = "memory",
        expression = "java( clusterEntity.getUsedMemoryNum()+ \"G/\" +clusterEntity.getAllMemoryNum()+\"G\")")
    @Mapping(target = "storage",
        expression = "java( clusterEntity.getUsedStorageNum()+ \"G/\"  +clusterEntity.getAllStorageNum()+\"G\")")
    @Mapping(target = "checkDateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PageClusterRes clusterEntityToPageClusterRes(ClusterEntity clusterEntity);

    @Mapping(target = "checkDateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    QueryAllClusterRes clusterEntityToQueryAllClusterRes(ClusterEntity clusterEntity);

    List<QueryAllClusterRes> clusterEntityListToQueryAllClusterResList(List<ClusterEntity> clusterList);
}
