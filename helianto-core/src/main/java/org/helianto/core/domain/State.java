/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * State of a union or federation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_state",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "stateCode"})}
)
public class State  
	implements Serializable, Comparable<State> {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="contextId", nullable=true)
    private Operator context;
    
    @Column(length=12)
    private String stateCode = "";
    
    @Column(length=64)
    private String stateName = "";
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="countryId")
    private Country country;
    
    private char priority = 0;

	/**
	 * Empty constructor.
	 */
    public State() {
        super();
    }

    /**
     * Key constructor.
     * 
     * @param context
     * @param stateCode
     */
    public State(Operator context, String stateCode) {
        this();
        setContext(context);
        setStateCode(stateCode);
    }

    /**
     * Country constructor.
     * 
     * @param country
     * @param stateCode
     */
    public State(Country country, String stateCode) {
        this(country.getOperator(), stateCode);
        setCountry(country);
    }

    /**
     * Name constructor.
     * 
     * @param operator
     * @param provinceCode
     * @param stateName
     */
    public State(Operator operator, String provinceCode, String stateName) {
    	this(operator, provinceCode);
    	setStateName(stateName);
    }

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Context.
     */
    public Operator getContext() {
		return context;
	}
    public void setContext(Operator context) {
		this.context = context;
	}
    
    /**
     * State code.
     */
    public String getStateCode() {
		return stateCode;
	}
    public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
    
    /**
     * State name.
     */
    public String getStateName() {
		return stateName;
	}
    public void setStateName(String stateName) {
		this.stateName = stateName;
	}

    /**
     * Country.
     */
    public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * State priority.
	 */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}

	public int compareTo(State next) {
		if (getPriority()==next.getPriority()) {
			if (getStateCode()!=null && next.getStateCode()!=null) {
				return getStateCode().compareTo(next.getStateCode());
			}
			return 0;
		}
		return getPriority()-next.getPriority();
	}
	
    /**
     * toString
     * @return String
     */
	public String toString() {
    	 return getStateCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result
				+ ((stateCode == null) ? 0 : stateCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		State other = (State) obj;
		if (context == null) {
			if (other.context != null) {
				return false;
			}
		} else if (!context.equals(other.context)) {
			return false;
		}
		if (stateCode == null) {
			if (other.stateCode != null) {
				return false;
			}
		} else if (!stateCode.equals(other.stateCode)) {
			return false;
		}
		return true;
	}
	
	

}


