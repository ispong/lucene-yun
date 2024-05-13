package com.isxcode.meta.modules.work.mapper;

import com.isxcode.meta.api.work.pojos.dto.SyncWorkConfig;
import com.isxcode.meta.api.work.pojos.res.GetSyncWorkConfigRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkConfigMapper {

    GetSyncWorkConfigRes syncWorkConfigToGetSyncWorkConfigRes(SyncWorkConfig syncWorkConfig);
}
