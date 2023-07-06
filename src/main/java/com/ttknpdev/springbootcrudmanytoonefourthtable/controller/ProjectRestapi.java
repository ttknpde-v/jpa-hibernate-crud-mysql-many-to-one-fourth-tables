package com.ttknpdev.springbootcrudmanytoonefourthtable.controller;

import com.ttknpdev.mycommonsresponse.common.ConstantsResponse;
import com.ttknpdev.mycommonsresponse.response.ResponseObject;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.Project;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectRestapi {
    private ProjectService<Project> service;
    @Autowired
    public ProjectRestapi(ProjectService<Project> service) {
        this.service = service;
    }
    @PostMapping(value = "/programmer/{p_id}/sale/{s_id}/marketing/{m_id}/create")
    private ResponseEntity create(@RequestBody Project project , @PathVariable Long p_id, @PathVariable Long s_id , @PathVariable Long m_id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Project>builder()
                .status(ConstantsResponse.STATUS_CREATED)
                .code(ConstantsResponse.STRING_CREATED)
                .object(service.create(p_id,s_id,m_id,project))
                .build());
    }
    @GetMapping(value = "/reads")
    private ResponseEntity reads(){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<List<Project>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.reads())
                .build());
    }
    @GetMapping(value = "/read/{project_name}")
    private ResponseEntity read(@PathVariable String project_name){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Project>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.read(project_name))
                .build());
    }


    @PutMapping(value = "/update/{project_name}")
    private ResponseEntity update(@PathVariable String project_name , @RequestBody Project project){
        return ResponseEntity.status(ConstantsResponse.STATUS_OK).body(ResponseObject.<Project>builder()
                .status(ConstantsResponse.STATUS_OK)
                .code(ConstantsResponse.STRING_OK)
                .object(service.update(project,project_name))
                .build());
    }

    @DeleteMapping(value = "/delete/{project_name}")
    private ResponseEntity delete(@PathVariable String project_name){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Map<String,Project>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.delete(project_name))
                .build());
    }
}
