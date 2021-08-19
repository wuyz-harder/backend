package com.example.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import com.example.myblog.entity.paper;
import java.util.List;

@Repository
@Mapper
public interface PaperMapper {
    public List<paper> getPaper();
    public void setpaper(paper paper);
}
