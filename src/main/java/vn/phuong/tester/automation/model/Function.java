/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * Job -
 *
 * @author phuong.nguyenthi
 */
@Entity
public class Function {

  private Long id;
  private String name;
  private int totalScript;
  private Project project;
  private String data;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column
  public int getTotalScript() {
    return totalScript;
  }

  public void setTotalScript(int totalScript) {
    this.totalScript = totalScript;
  }

  @Column(length = 100, nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ManyToOne(optional = false)
  @JoinColumn(name = "project")
  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Lob
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
