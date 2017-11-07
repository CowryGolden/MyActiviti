package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.Project;

public interface ProjectMapper extends BaseMapper<Project> {
    List<Project> getPrjByCoId(String coId);
}