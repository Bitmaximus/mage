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
package mage.sets.judgment;

import java.util.UUID;
import mage.MageObject;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.costs.common.TapTargetCost;
import mage.abilities.effects.PreventionEffectImpl;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.choices.ChoiceColor;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.constants.TimingRule;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.filter.predicate.permanent.TappedPredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author escplan9 (Derek Monturo - dmontur1 at gmail dot com)
 */
public class PrismaticStrands extends CardImpl {
    
    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("untapped white creature you control");
    static {
        filter.add(Predicates.not(new TappedPredicate()));
        filter.add(new ColorPredicate(ObjectColor.WHITE));
    }

    public PrismaticStrands(UUID ownerId) {
        super(ownerId, 18, "Prismatic Strands", Rarity.COMMON, new CardType[]{CardType.INSTANT}, "{2}{W}");
        this.expansionSetCode = "JUD";

        // Prevent all damage that sources of the color of your choice would deal this turn.
        this.getSpellAbility().addEffect(new PrismaticStrandsEffect(Duration.EndOfTurn, Integer.MAX_VALUE, false));
        this.getSpellAbility().addChoice(new ChoiceColor());
        
        // Flashback-Tap an untapped white creature you control.
        this.addAbility(new FlashbackAbility(new TapTargetCost(new TargetControlledCreaturePermanent(1,1,filter,false)), TimingRule.INSTANT));
    }

    public PrismaticStrands(final PrismaticStrands card) {
        super(card);
    }

    @Override
    public PrismaticStrands copy() {
        return new PrismaticStrands(this);
    }
}

class PrismaticStrandsEffect extends PreventionEffectImpl {

    public PrismaticStrandsEffect(Duration duration, int amount, boolean onlyCombat) {
        super(duration, amount, onlyCombat, false);
        this.staticText = "Prevent all damage that sources of the color of your choice would deal this turn";
    }

    public PrismaticStrandsEffect(PrismaticStrandsEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (super.applies(event, source, game)) {
            if (event.getType().equals(GameEvent.EventType.DAMAGE_PLAYER) 
                    || event.getType().equals(GameEvent.EventType.DAMAGE_CREATURE) 
                    || event.getType().equals(GameEvent.EventType.DAMAGE_PLANESWALKER)) {
                ChoiceColor choice = (ChoiceColor) source.getChoices().get(0);
                MageObject sourceObject = game.getObject(event.getSourceId());
                if (sourceObject != null && choice != null && sourceObject.getColor(game).shares(choice.getColor())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public PrismaticStrandsEffect copy() {
        return new PrismaticStrandsEffect(this);
    }
}