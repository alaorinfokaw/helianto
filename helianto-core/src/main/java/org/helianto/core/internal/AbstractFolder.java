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

package org.helianto.core.internal;

import javax.persistence.Column;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.type.FolderEntity;

/**
 * Base class to represent a folder.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractFolder
	extends AbstractCounter
	implements FolderEntity {

	private static final long serialVersionUID = 1L;
	
    // TODO rename in table
    @Column(length=36, name="builderCode")
	private String folderCode = "";
	
    // TODO rename in table
    @Column(length=128, name="builderName")
	private String folderName = "";
	
    @Column(length=64)
	private String folderDecorationUrl = "";
    
    /** 
     * Empty constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public AbstractFolder() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public AbstractFolder(Entity entity, String folderCode) {
    	this();
    	setEntity(entity);
    	setFolderCode(folderCode);
    }
    
    /**
     * Form constructor.
     * 
     * @param id
     * @param folderCode
     * @param folderName
     * @param folderDecorationUrl
     */
    protected AbstractFolder(int id, String folderCode, String folderName, String folderDecorationUrl) {
		super();
		setId(id);
		this.folderCode = folderCode;
		this.folderName = folderName;
		this.folderDecorationUrl = folderDecorationUrl;
	}

	public String getFolderCode() {
        return getInternalBuilderCode();
    }
    public void setFolderCode(String builderCode) {
        this.folderCode = builderCode;
    }
    
    /**
     * Sublcasses may change the way a folder code is retrieved.
     */
    protected String getInternalBuilderCode() {
        return this.folderCode;
    }

	public String getFolderName() {
		return getInternalBuilderName();
	}
	public void setFolderName(String builderName) {
		this.folderName = builderName;
	}
	
    /**
     * Sublcasses may change the way a folder name is retrieved.
     */
    protected String getInternalBuilderName() {
    	return folderName;
    }
    
    /**
     * Folder decoration url.
     */
    public String getFolderDecorationUrl() {
		return folderDecorationUrl;
	}
    public void setFolderDecorationUrl(String folderDecorationUrl) {
		this.folderDecorationUrl = folderDecorationUrl;
	}
    
    /**
     * True if {@link #getFolderDecorationUrl()} is not empty.
     */
    public boolean isFolderDecorated() {
		if (getFolderDecorationUrl()!=null && getFolderDecorationUrl().length()>0) {
			return true;
		}
		return false;
	}

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("folderCode").append("='").append(getFolderCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }
    
    /**
     * Merger.
     * 
     * @param command
     */
    protected void merge(AbstractFolder command) {
    	setFolderName(command.getFolderName());
    	setFolderDecorationUrl(command.getFolderDecorationUrl());
    }

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof AbstractFolder) ) return false;
		AbstractFolder castOther = (AbstractFolder) other; 
         
         return ( ( this.getEntity()==castOther.getEntity()) 
        		    || ( this.getEntity()!=null 
        			     && castOther.getEntity()!=null 
        			     && this.getEntity().equals(castOther.getEntity()
        			   ) 
        		))
             && ( ( this.getFolderCode()==castOther.getFolderCode()) 
            	    || ( this.getFolderCode()!=null 
            	    	 && castOther.getFolderCode()!=null 
            	    	 && this.getFolderCode().equals(castOther.getFolderCode()
            	    	)
            	));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getFolderCode() == null ? 0 : this.getFolderCode().hashCode() );
         return result;
   }   

}
