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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Project - Project entity
 *
 * @author phuong.nguyenthi
 */
@Entity
public class Project {

  private Long id;
  private String name;
  private String description;
  private User owner;
  private Collection<Function> functions;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(length = 100, nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 255)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @ManyToOne(optional = false)
  @JoinColumn(name = "owner")
  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  @OneToMany
  @JoinColumn(name = "project")
  public Collection<Function> getFunctions() {
    return functions;
  }

  public void setFunctions(Collection<Function> functions) {
    this.functions = functions;
  }
}
