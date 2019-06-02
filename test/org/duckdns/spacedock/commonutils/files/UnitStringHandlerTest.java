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

import java.util.Locale;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ykonoclast
 */
public class UnitStringHandlerTest
{

    private final StringHandler handler = new StringHandler(getClass().getPackage().getName().concat("/resources"), Locale.getDefault());
    private final StringHandler handlerKO = new StringHandler(getClass().getPackage().getName().concat("/resources"), Locale.KOREA);

    @Test
    public void testGetStringDefaultLocaleNominal()
    {
	String prop = handler.getString("param_aberr");
	assertEquals(prop, "paramétre aberrant:");

	prop = handler.getString("mauv_meth");
	assertEquals(prop, "emploi de la mauvaise méthode dans ce contexte:");

    }

    @Test
    public void testGetErrorMessageDefaultLocaleNominal()
    {
	String prop = handler.getErrorMessage("JSON");
	assertEquals(prop, "erreur d'accès JSON:");

	prop = handler.getErrorMessage("properties");
	assertEquals(prop, "erreur d'accès à des propriétés:");
    }

    @Test
    public void testGetStringSpecificLocaleNominal()
    {
	String prop = handlerKO.getString("un");
	assertEquals(prop, "deuxKO");

	prop = handlerKO.getString("trois");
	assertEquals(prop, "quatreKO");
    }

    @Test
    public void testGetErrorMessageSpecificLocaleNominal()
    {
	String prop = handlerKO.getErrorMessage("msg1");
	assertEquals(prop, "erreur1KO");

	prop = handlerKO.getErrorMessage("msg2");
	assertEquals(prop, "erreur2KO");
    }

    @Test
    public void testGetErrorMessageError()
    {
	String prop = handlerKO.getErrorMessage("TAGAZOK");

	assertEquals("", prop);
	prop = handler.getString("blurp");
	assertEquals("", prop);
    }

}
