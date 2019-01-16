package com.lumia.lostfound.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.dao.ThingMapper;
import com.lumia.lostfound.dto.ThingDTO;
import com.lumia.lostfound.dto.UserDTO;
import com.lumia.lostfound.entity.Thing;
import com.lumia.lostfound.entity.Type;
import com.lumia.lostfound.exception.ErrorArgumentException;
import com.lumia.lostfound.service.ThingService;
import com.lumia.lostfound.service.TypeService;
import com.lumia.lostfound.service.UserService;
import com.lumia.lostfound.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("thingService")
public class ThingServiceImpl implements ThingService {

    @Autowired
    private ThingMapper thingMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;

    @Override
    public boolean add(Thing thing) {
        if (thing == null){
            return false;
        }
        thing.setStatus(0);
        thing.setGmtCreate(new Timestamp(new Date().getTime()));
        thing.setGmtModified(new Timestamp(new Date().getTime()));
        int insert = thingMapper.insert(thing);
        if (insert > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(Thing thing) {
        if (thing == null || thing.getThingId() == null){
            return false;
        }
        thing.setGmtModified(new Timestamp(new Date().getTime()));
        int update = thingMapper.updateById(thing);
        if (update > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(Long thingId) {
        Thing thing = thingMapper.selectById(thingId);
        if (thing == null){
            throw new ErrorArgumentException("未查找要删除的对象");
        }
        String imgs = thing.getImgs();
        int delete = thingMapper.deleteById(thingId);
        if (imgs != ""  || imgs != null){
            if (imgs.contains(",")){
                String[] imgPaths = imgs.split(",");
                for (int i=0; i<imgPaths.length; i++){
                    FileUtils.deleteFile(imgPaths[i]);  //删除多张图片
                }
            }else{
                FileUtils.deleteFile(imgs);  //删除单张图片
            }
        }
        if (delete > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ThingDTO findOne(Long thingId) {
        if (thingId == null){
            return null;
        }
        Thing thing = thingMapper.selectById(thingId);
        if (thing == null){
            throw new ErrorArgumentException("未查询到结果");
        }
        return assembleThing(thing);
    }

    @Override
    public List<ThingDTO> findByUser(String userId) {
        Wrapper<Thing> wrapper = new QueryWrapper<>();
        ((QueryWrapper<Thing>) wrapper).eq("user_id", userId);
        ((QueryWrapper<Thing>) wrapper).orderByDesc("gmt_modified");
        List<ThingDTO> thingDTOList = new ArrayList<>();
        List<Thing> thingList = thingMapper.selectList(wrapper);
        for (Thing thing : thingList){
            thingDTOList.add(assembleThing(thing));
        }
        return thingDTOList;
    }

    @Override
    public Page<ThingDTO> findPage(Page page, Integer kind, Integer typeId, Integer status) {
        Wrapper<Thing> wrapper = new QueryWrapper<>();
        if (kind != null){
            ((QueryWrapper<Thing>) wrapper).eq("kind", kind);
        }
        if (typeId != null){
            ((QueryWrapper<Thing>) wrapper).eq("type_id", typeId);
        }
        if (status != null){
            ((QueryWrapper<Thing>) wrapper).eq("status", status);
        }
        ((QueryWrapper<Thing>) wrapper).orderByDesc("gmt_create");
        IPage<Thing> iPage = thingMapper.selectPage(page, wrapper);
        return assemblePage((Page<Thing>) iPage);
    }

    /**
     * 分页对象
     * @param page
     * @return
     */
    private Page<ThingDTO> assemblePage(Page<Thing> page){
        Page<ThingDTO> thingDTOPage = new Page<>();
        thingDTOPage.setTotal(page.getTotal());
        thingDTOPage.setSize(page.getSize());
        thingDTOPage.setCurrent(page.getCurrent());
        thingDTOPage.setPages(page.getPages());
        List<Thing> records = page.getRecords();
        List<ThingDTO> thingDTOList = new ArrayList<>();
        for (Thing thing : records){
            thingDTOList.add(assembleThing(thing));
        }
        thingDTOPage.setRecords(thingDTOList);
        return thingDTOPage;
    }


    /**
     * 组装物品传输对象
     * @param thing
     * @return
     */
    private ThingDTO assembleThing(Thing thing){
        ThingDTO thingDTO = new ThingDTO();
        thingDTO.setThingId(thing.getThingId());
        UserDTO userDTO = userService.findByOpenId(thing.getUserId());
        thingDTO.setUserDTO(userDTO);
        thingDTO.setKind(thing.getKind());
        Type type = typeService.findById(thing.getTypeId());
        thingDTO.setType(type);
        thingDTO.setName(thing.getName());
        thingDTO.setPlace(thing.getPlace());
        thingDTO.setStatus(thing.getStatus());
        thingDTO.setDetail(thing.getDetail());
        thingDTO.setImgs(thing.getImgs());
        thingDTO.setGmtCreate(thing.getGmtCreate());
        thingDTO.setGmtModified(thing.getGmtModified());
        return thingDTO;
    }
}
