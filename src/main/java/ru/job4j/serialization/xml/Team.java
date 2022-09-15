package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;


@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class Team {

    @XmlAttribute
    private boolean qualified;

    @XmlAttribute
    private int point;

    @XmlAttribute
    private String name;

    @XmlElementWrapper(name = "lineUpes")
    private String[] lineUp;
    private Match match;

    public Team() {

    }

    public Team(boolean qualified, int point, String name, String[] lineUp,
                Match match) {
        this.qualified = qualified;
        this.point = point;
        this.name = name;
        this.lineUp = lineUp;
        this.match = match;
    }

    @Override
    public String toString() {
        return "Team{"
                + "qualified=" + qualified
                + ", point=" + point
                + ", name='" + name + '\''
                + ", lineUp=" + Arrays.toString(lineUp)
                + ", match=" + match
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        Team team = new Team(true, 3, "RD", new String[]{"Garry", "Ron"},
                new Match("NY", 6, 3));
        JAXBContext context = JAXBContext.newInstance(Team.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(team, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Team result = (Team) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}