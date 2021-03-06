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
package mage.sets.mirrodinbesieged;

import java.util.Set;
import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DrawCardControllerTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.common.CardsInControllerHandCount;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.SetPowerToughnessSourceEffect;
import mage.cards.CardImpl;
import mage.constants.Duration;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author North
 */
public class PsychosisCrawler extends CardImpl {

    public PsychosisCrawler(UUID ownerId) {
        super(ownerId, 126, "Psychosis Crawler", Rarity.RARE, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{5}");
        this.expansionSetCode = "MBS";
        this.subtype.add("Horror");

        this.power = new MageInt(0);
        this.toughness = new MageInt(0);

        this.addAbility(new SimpleStaticAbility(Zone.ALL, new SetPowerToughnessSourceEffect(new CardsInControllerHandCount(), Duration.EndOfGame)));
        this.addAbility(new DrawCardControllerTriggeredAbility(new LoseLifeOpponentsEffect(), false));
    }

    public PsychosisCrawler(final PsychosisCrawler card) {
        super(card);
    }

    @Override
    public PsychosisCrawler copy() {
        return new PsychosisCrawler(this);
    }
}

class LoseLifeOpponentsEffect extends OneShotEffect {

    public LoseLifeOpponentsEffect() {
        super(Outcome.Damage);
        staticText = "each opponent loses 1 life";
    }

    public LoseLifeOpponentsEffect(final LoseLifeOpponentsEffect effect) {
        super(effect);
    }

    @Override
    public LoseLifeOpponentsEffect copy() {
        return new LoseLifeOpponentsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        boolean applied = false;
        Set<UUID> opponents = game.getOpponents(source.getControllerId());
        for (UUID opponentUUID : opponents) {
            Player player = game.getPlayer(opponentUUID);
            if (player != null) {
                player.loseLife(1, game);
                applied = true;
            }
        }
        return applied;
    }

}