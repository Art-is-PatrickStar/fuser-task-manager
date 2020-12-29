package com.wsw.fusertaskmanager.repository;

import com.wsw.fusertaskmanager.entity.Good;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author WangSongWen
 * @Date: Created in 16:05 2020/12/29
 * @Description:
 */
public interface GoodsRepository extends ElasticsearchRepository<Good, Integer> {
}
