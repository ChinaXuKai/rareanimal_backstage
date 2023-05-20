package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.AnimalDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Animal;
import com.guangyou.rareanimal_backstage.pojo.vo.AnimalVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 10:38
 */
@RestController
@RequestMapping("/animal")
@Slf4j
@Api(tags = "动物控制器")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @ApiOperation(value = "根据动物id获取该动物的详情信息")
    @GetMapping("/getAnimalDetailByAid")
    public Result getAnimalDetailByAid(Long animalId){
        return animalService.getAnimalDetailByAid(animalId);
    }

    @ApiOperation(value = "分页显示动物信息",notes = "分页显示动物信息")
    @GetMapping("getAnimalList")
    public Result getAnimalList(PageDto pageDto){
        PageDataVo<AnimalVo> pageDataVo = animalService.getAnimalsByPage(pageDto);
        if (pageDataVo.getPageData().isEmpty()){
            return Result.fail("分页获取动物信息错误");
        }
        return Result.succ(200, "分页获取动物信息成功，当前第"+pageDto.getPage()+"页，每页"+pageDto.getPageSize()+"条数据",
                pageDataVo);
    }

    @ApiOperation(value = "新增动物",notes = "新增动物")
    @PostMapping("addAnimal")
    public Result addAnimal(@RequestBody @Validated AnimalDto animalDto){
        return animalService.addAnimal(animalDto);
    }


    @ApiOperation(value = "修改动物信息",notes = "修改动物信息")
    @PutMapping("updateAnimal")
    public Result updateAnimal(@RequestBody @Validated AnimalDto animalDto){
        int animalId = animalService.updateAnimal(animalDto);
        if (animalId == 0){
            return Result.fail("修改动物失败，要修改的动物id为：" + animalId,animalId);
        }
        return Result.succ(200, "修改动物成功，被修改的动物id为：" + animalId, animalId);
    }


    @ApiOperation(value = "根据动物id删除动物信息",notes = "删除动物信息")
    @PutMapping("deleteAnimal/{animalId}")
    public Result deleteAnimal(@PathVariable("animalId") Integer animalId){
        int deleteAnimalId = animalService.deleteAnimalById(animalId);
        if (deleteAnimalId == 0){
            return Result.fail("删除失败，要删除的动物id为：",animalId);
        }
        return Result.succ(200,"删除成功",deleteAnimalId);
    }


    @ApiOperation(value = "根据动物名称进行模糊查询动物",notes = "查询特定名称动物的相关信息（模糊查询）")
    @GetMapping("/getAnimalInfo")
    public Result getAnimalInfo(String animalLike){
        List<AnimalVo> likeAnimals = animalService.selectAnimalByLike(animalLike);

        if (likeAnimals.isEmpty()){
            return Result.fail(Result.FORBIDDEN,"未查找到你指定的动物",null);
        }else {
            return Result.succ(200,"已查找到你指定的动物",likeAnimals);
        }
    }
}
