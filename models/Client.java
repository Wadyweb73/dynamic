package models;

public class Client {
	private String name;
	private String BI;
	private String email;
	private String tell;
	private String residence;
	private String clientCode;

	public String getName      () { return name;       }
	public String getBI        () { return BI;         }
	public String getEmail     () { return email;      }
	public String getTell      () { return tell;       }
	public String getResidence () { return residence;  }
	public String getClientCode() { return clientCode; }

	public void setName(String name) {
		this.name = name;
	}
	public void setClientCode(String client_code) {
		this.clientCode = client_code;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBI(String BI) {
		this.BI = BI;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public void setResidence(String res) {
		this.residence = res;
	}
}
