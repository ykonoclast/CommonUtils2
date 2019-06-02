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
package org.duckdns.spacedock.commonutils.files;

import java.io.FileNotFoundException;
import javax.json.JsonObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class UnitJSONHandlerTest//on ne mocke pas, les mocks sont plus utiles pour les moments où il faut contrôler les valeurs de retour
{

    private JSONHandler handler = new JSONHandler(getClass().getPackage().getName().concat("/resources"));

    @Test
    public void testloadJsonFileNominal() throws FileNotFoundException
    {

	//extension .json
	JsonObject object = handler.loadJsonFile("json.json");
	assertEquals((object.getString("name")), "json.json");

	//extension .JSON
	object = handler.loadJsonFile("json.JSON");
	assertEquals((object.getString("name")), "json.JSON");

	//extension non mentionnée
	object = handler.loadJsonFile("jsonsansext");
	assertEquals((object.getString("name")), "jsonsansext");
    }

    @Test
    public void testloadJsonFileSousRep() throws FileNotFoundException
    {

	//test sous-répertoire
	JsonObject object = handler.loadJsonFile("sousrep/sousrep.json");
	assertEquals((object.getString("name")), "sousrep.json");
    }

    @Test
    public void testloadJsonFileErreur() throws FileNotFoundException
    {
	try
	{
	    //test émission exception si fichier non trouvé
	    handler.loadJsonFile("tarace");
	    fail();
	}
	catch (FileNotFoundException e)
	{
	    assertEquals("fichier introuvable:erreur d'accès JSON:tarace", e.getMessage());
	}
    }
}
