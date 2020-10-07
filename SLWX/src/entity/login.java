package entity;

public class login {
 private String name;
 private String pwd;
 private String integral;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getIntegral() {
	return integral;
}
public void setIntegral(String integral) {
	this.integral = integral;
}
@Override
public String toString() {
	return "login [name=" + name + ", pwd=" + pwd + ", integral=" + integral + "]";
}
public login(String name, String pwd, String integral) {
	super();
	this.name = name;
	this.pwd = pwd;
	this.integral = integral;
}
public login( String integral) {
	super();
	this.integral = integral;
}
public login(String name, String integral) {
	super();
	this.name = name;
	this.integral = integral;
}
public login() {
	super();

}
 
 
}
