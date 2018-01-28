package me.alexandreh.fr.mananaheul.mana;

import java.util.HashMap;

public class Hash {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mana == null) ? 0 : mana.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hash other = (Hash) obj;
		if (mana == null) {
			if (other.mana != null)
				return false;
		} else if (!mana.equals(other.mana))
			return false;
		return true;
	}

	private HashMap<String, Integer> mana = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> getMana(){
		return mana;
	}
	
}
