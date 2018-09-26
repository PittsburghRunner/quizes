/*
 * Copyright (C) 2018 christopher.eckles
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package quizes;

import java.util.Random;

/**
 *
 * @author christopher.eckles
 */
public class RandomNumber {

    // generate a random number
    public static int generateRandomNumber(int maxInt) {
        // create a new random number object
        if (maxInt > 0) {
            Random r = new Random();
            //create a int amd stpre tje next int insid it
            int next = r.nextInt(maxInt);
            //System.out.println("Random Number: " + next);
            //return the value
            return next;
        }
    return 0;
    }
}
