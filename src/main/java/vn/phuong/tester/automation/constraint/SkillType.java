package vn.phuong.tester.automation.constraint;

public enum SkillType {
	OPEN, CLICK, INPUT, SELECT, VERIFY, UNKNOWN;

	public static String preProcess(String input){
		return input.toUpperCase().trim();
	}
}
