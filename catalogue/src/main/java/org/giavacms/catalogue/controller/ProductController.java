/*
 * Copyright 2013 GiavaCms.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.giavacms.catalogue.controller;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.base.controller.AbstractPageWithImagesAndDocumentsController;
import org.giavacms.base.model.attachment.Document;
import org.giavacms.base.model.attachment.Image;
import org.giavacms.catalogue.model.Category;
import org.giavacms.catalogue.model.Product;
import org.giavacms.catalogue.repository.CatalogueConfigurationRepository;
import org.giavacms.catalogue.repository.CategoryRepository;
import org.giavacms.catalogue.repository.ProductRepository;
import org.giavacms.common.annotation.BackPage;
import org.giavacms.common.annotation.EditPage;
import org.giavacms.common.annotation.ListPage;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.annotation.ViewPage;

@Named
@SessionScoped
public class ProductController extends AbstractPageWithImagesAndDocumentsController<Product>
{

   private static final long serialVersionUID = 1L;


   // --------------------------------------------------------
   @BackPage
   public static String BACK = "/private/administration.xhtml";
   @ViewPage
   public static String VIEW = "/private/catalogue/view.xhtml";
   @ListPage
   public static String LIST = "/private/catalogue/list.xhtml";
   @EditPage
   public static String NEW_OR_EDIT = "/private/catalogue/edit.xhtml";

   private static final String EDIT_DOCS = "/private/catalogue/edit-documents.xhtml";

   // ------------------------------------------------

   @Inject
   @OwnRepository(ProductRepository.class)
   ProductRepository prodottiRepository;

   @Inject
   CatalogueConfigurationRepository catalogueConfigurationRepository;

   @Inject
   CategoryRepository categoryRepository;

   @Override
   public String getExtension()
   {
      return Product.EXTENSION;
   }

   // -------------------------------------------------

   public void loadCategory()
   {
      if (getElement().getCategory().getId() == null)
      {
         getElement().setCategory(new Category());
         for (int i = 1; i <= 10; i++)
         {
            getElement().setVal(i, null);
         }
      }
      else
      {
         getElement().setCategory(categoryRepository.find(getElement().getCategory().getId()));
         for (int i = 1; i <= 10; i++)
         {
            getElement().setVal(i, null);
         }
      }
   }
   
   @Override
   protected List<Document> getElementDocuments()
   {
      return getElement().getDocuments();
   }

   @Override
   protected List<Image> getElementImages()
   {
      return getElement().getImages();
   }

   @Override
   public String editDocsPage()
   {
      return EDIT_DOCS;
   }

}
