/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.visitor;

import vn.phuong.tester.automation.skill.ClickSkill;
import vn.phuong.tester.automation.skill.InputSkill;
import vn.phuong.tester.automation.skill.OpenSkill;
import vn.phuong.tester.automation.skill.SelectionSkill;
import vn.phuong.tester.automation.skill.UnknownSkill;
import vn.phuong.tester.automation.skill.VerifySkill;

/**
 * SkillVisitor
 *
 * @author phuong.nguyenthi
 */
public interface SkillVisitor {

  boolean visit(OpenSkill openSkill);

  boolean visit(InputSkill inputSkill);

  boolean visit(ClickSkill clickSkill);

  boolean visit(VerifySkill verifySkill);

  boolean visit(SelectionSkill selectionSkill);

  boolean visit(UnknownSkill unknownSkill);

}
