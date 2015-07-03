/*
 * Copyright 2013 GiavaCms.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.giavacms.catalogue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class CatalogueConfiguration implements Serializable
{
   private static final long serialVersionUID = 1L;

   private Long id;
   private boolean resize;
   private int maxWidthOrHeight;
   private boolean withPrices;
   private boolean withDimensions;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public boolean isResize()
   {
      return resize;
   }

   public void setResize(boolean resize)
   {
      this.resize = resize;
   }

   public int getMaxWidthOrHeight()
   {
      return maxWidthOrHeight;
   }

   public void setMaxWidthOrHeight(int maxWidthOrHeight)
   {
      this.maxWidthOrHeight = maxWidthOrHeight;
   }

   @Override
   public String toString()
   {
      return "CatalogueConfiguration [id=" + id + ", resize=" + resize
               + ", maxWidthOrHeight=" + maxWidthOrHeight + "]";
   }

   public boolean isWithPrices()
   {
      return withPrices;
   }

   public void setWithPrices(boolean withPrices)
   {
      this.withPrices = withPrices;
   }

   public boolean isWithDimensions()
   {
      return withDimensions;
   }

   public void setWithDimensions(boolean withDimensions)
   {
      this.withDimensions = withDimensions;
   }

}
