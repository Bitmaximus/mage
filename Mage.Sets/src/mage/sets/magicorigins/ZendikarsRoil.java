/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.magicorigins;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.filter.common.FilterLandPermanent;
import mage.game.permanent.token.Token;

/**
 *
 * @author LevelX2
 */
public class ZendikarsRoil extends CardImpl {

    public ZendikarsRoil(UUID ownerId) {
        super(ownerId, 209, "Zendikar's Roil", Rarity.UNCOMMON, new CardType[]{CardType.ENCHANTMENT}, "{3}{G}{G}");
        this.expansionSetCode = "ORI";

        // Whenever a land enters the battlefield under your control, put a 2/2 green Elemental creature token onto the battlefield.
        Effect effect = new CreateTokenEffect(new ZendikarsRoilElementalToken());
        this.addAbility(new EntersBattlefieldControlledTriggeredAbility(effect, new FilterLandPermanent("a land")));
    }

    public ZendikarsRoil(final ZendikarsRoil card) {
        super(card);
    }

    @Override
    public ZendikarsRoil copy() {
        return new ZendikarsRoil(this);
    }
}

class ZendikarsRoilElementalToken extends Token {

    public ZendikarsRoilElementalToken() {
        super("Elemental", "2/2 green Elemental creature token");
        cardType.add(CardType.CREATURE);
        setOriginalExpansionSetCode("ORI");
        subtype.add("Elemental");
        color.setGreen(true);
        power = new MageInt(2);
        toughness = new MageInt(2);
    }

}
