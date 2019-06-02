/*
 * Copyright (C) 2019 ykonoclast
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.duckdns.spacedock.commonutils.testfiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import javax.json.JsonObject;
import org.duckdns.spacedock.commonutils.files.GeneralFileHandler;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author ykonoclast
 */
public class IntegFilesHandlerTest
{

    @Test
    public void stringsTest()
    {
	//locale par défaut
	assertEquals("tarace", GeneralFileHandler.getInstance(getClass().getPackage().getName()).getString("tarass", Locale.getDefault()));
	assertEquals("boulba", GeneralFileHandler.getInstance(getClass().getPackage().getName()).getString("boulbah", Locale.getDefault()));

	//locale définie
	assertEquals("taracekoko", GeneralFileHandler.getInstance(getClass().getPackage().getName()).getString("tarass", Locale.KOREA));
	assertEquals("boulbakoko", GeneralFileHandler.getInstance(getClass().getPackage().getName()).getString("boulbah", Locale.KOREA));

	//locale n'existant pas : fallback sur défaut
	assertEquals("tarace", GeneralFileHandler.getInstance(getClass().getPackage().getName()).getString("tarass", Locale.ITALIAN));
	assertEquals("boulba", GeneralFileHandler.getInstance(getClass().getPackage().getName()).getString("boulbah", Locale.ITALIAN));

	//string n'existant pas
	assertEquals("", GeneralFileHandler.getInstance(getClass().getPackage().getName()).getString("jexistepas!", Locale.getDefault()));
    }

    @Test
    public void JSONTest() throws FileNotFoundException
    {

	JsonObject object = GeneralFileHandler.getInstance(getClass().getPackage().getName()).loadJsonFile("newjson.json");
	assertEquals("ykonoclast", object.getString("name"));

    }

    @Test
    public void propTest() throws FileNotFoundException, IOException
    {
	String prop = GeneralFileHandler.getInstance(getClass().getPackage().getName()).getAppProperty("newproperties", "meugni");
	assertEquals("gueubrut", prop);
    }

    @Test
    public void erreurTestAvecMsg()
    {
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep1", ":blurk", Locale.getDefault());
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero1:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep2", ":blurk", Locale.getDefault());
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero2:blurk", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep1", ":blurk", Locale.KOREA);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero1koko:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep2", ":blurk", Locale.KOREA);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero2koko:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep1", ":blurk", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero1:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep2", ":blurk", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero2:blurk", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep1", ":blurk", Locale.getDefault());
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero1:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep2", ":blurk", Locale.getDefault());
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero2:blurk", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep1", ":blurk", Locale.KOREA);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero1koko:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep2", ":blurk", Locale.KOREA);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero2koko:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep1", ":blurk", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero1:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep2", ":blurk", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero2:blurk", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep1", ":blurk", Locale.getDefault());
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero1:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep2", ":blurk", Locale.getDefault());
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero2:blurk", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep1", ":blurk", Locale.KOREA);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero1koko:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep2", ":blurk", Locale.KOREA);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero2koko:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep1", ":blurk", Locale.ITALIAN);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero1:blurk", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep2", ":blurk", Locale.ITALIAN);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero2:blurk", e.getMessage());
	}
    }

    @Test
    public void erreurTestSansMsg()
    {

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep1", Locale.getDefault());
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero1", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep2", Locale.getDefault());
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero2", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep1", Locale.KOREA);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero1koko", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep2", Locale.KOREA);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero2koko", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep1", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero1", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).paramAberrant("excep2", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalArgumentException e)
	{
	    assertEquals("paramétre aberrant:exceptionnumero2", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep1", Locale.getDefault());
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero1", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep2", Locale.getDefault());
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero2", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep1", Locale.KOREA);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero1koko", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep2", Locale.KOREA);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero2koko", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep1", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero1", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).mauvaiseMethode("excep2", Locale.ITALIAN);
	    fail();
	}
	catch (IllegalStateException e)
	{
	    assertEquals("emploi de la mauvaise méthode dans ce contexte:exceptionnumero2", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep1", Locale.getDefault());
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero1", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep2", Locale.getDefault());
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero2", e.getMessage());
	}

	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep1", Locale.KOREA);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero1koko", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep2", Locale.KOREA);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero2koko", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep1", Locale.ITALIAN);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero1", e.getMessage());
	}
	try
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("excep2", Locale.ITALIAN);
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:exceptionnumero2", e.getMessage());
	}
    }
}
