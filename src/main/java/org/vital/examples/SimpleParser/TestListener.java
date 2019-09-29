package org.vital.examples.SimpleParser;

public class TestListener extends SimpleBaseListener {

    public TestListener() {
        System.out.println("Constructed.");
    }

    @Override
    public void enterSimple(SimpleParser.SimpleContext ctx) {
        super.enterSimple(ctx);
        System.out.println("Parsing...");
    }

    @Override
    public void enterHostdef(SimpleParser.HostdefContext ctx) {
        super.enterHostdef(ctx);
        System.out.println("found HOSTs: " + ctx.NAME().size());
    }

    @Override
    public void enterAssignment(SimpleParser.AssignmentContext ctx) {
        super.enterAssignment(ctx);
    }
}
