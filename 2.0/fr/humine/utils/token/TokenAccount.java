package fr.humine.utils.token;

import java.io.Serializable;

/**
 * Classe reprensentant un compte bancaire de token
 * 
 * @author miza
 *
 */
public class TokenAccount implements Serializable{

	private static final long serialVersionUID = 4968151659799426878L;
	private String owner;
	private int token;

	public TokenAccount(String owner, int token) {
		this.owner = owner;
		this.token = token;
	}

	public TokenAccount(String owner) {
		this(owner, 0);
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + token;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenAccount other = (TokenAccount) obj;
		if (owner == null)
		{
			if (other.owner != null)
				return false;
		}
		else if (!owner.equals(other.owner))
			return false;
		if (token != other.token)
			return false;
		return true;
	}
	
	
}
