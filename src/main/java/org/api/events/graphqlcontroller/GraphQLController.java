package org.api.events.graphqlcontroller;

import org.api.events.models.GoldRates;
import org.api.events.models.Relative;
import org.api.events.models.SilverRates;
import org.api.events.service.goldapi.GoldSilverService;
import org.api.events.service.relativeservice.IRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;


/**
 *
 * <b>Developing GraphQL methods </b>
 * @author yogeshjoga
 */
@Controller
public class GraphQLController {

    @Autowired
    private IRelativeService relativeService;
    @Autowired
    private GoldSilverService goldSilverService;


    // We have Two types of GraphQL mappings
    // one QueryMapping
    // mutationmapping




    @QueryMapping
    public List<Relative> getAllRelatives() {
        return relativeService.graphAllRelatives();
    }


    @MutationMapping
    public Relative postRelative() {
        return null;
    }

    @QueryMapping
    public GoldRates getGoldRates() {
        return goldSilverService.getGoldRates();
    }

    @QueryMapping
    public SilverRates getSilverRates() {
        return goldSilverService.getSilverRates();
    }

}
