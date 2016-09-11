/*
 * Copyright (c) 2015 Ekino
 */
package vn.phuong.tester.automation.util;

import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.bean.ProjectBean;
import vn.phuong.tester.automation.model.Project;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * BeanConvertor -
 *
 * @author phuong.nguyenthi
 */
public class ProjectConvertor {

  public static Function<ProjectBean, Project> convertProject = projectBean -> {
    if (projectBean == null) {
      return null;
    }
    Project project = new Project();
    project.setId(projectBean.getId());
    project.setName(projectBean.getName());
    project.setDescription(projectBean.getDescription());
    project.setOwner(UserConvertor.convertUser.apply(projectBean.getOwner()));
    List<vn.phuong.tester.automation.model.Function> list = projectBean.getFunctions().stream().map(FunctionConvertor.convertFunction).collect(Collectors.<vn.phuong.tester.automation.model.Function> toList());
    project.setFunctions(list);
    return project;
  };

  public static Function<Project, ProjectBean> convertProjectBean = project -> {
    if (project == null) {
      return null;
    }
    List<FunctionBean> list = project.getFunctions().stream().map(FunctionConvertor.convertFunctionBean).collect(Collectors.<FunctionBean> toList());
    return new ProjectBean (
      project.getId(),
      project.getName(),
      project.getDescription(),
      UserConvertor.convertUserBean.apply(project.getOwner()),
      list);
  };

}
