package umleditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.StringsCompleter;

public class TabCompletion {

    private AggregateCompleter comp;

    public TabCompletion(){
         this.comp = new AggregateCompleter(
                            new ArgumentCompleter(
                                new StringsCompleter("add"),
                                new StringsCompleter("class", "method", "field", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("delete"),
                                new StringsCompleter("class", "method", "field", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("rename"),
                                new StringsCompleter("class", "method", "field", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("add"),
                                new StringsCompleter("relationship"),
                                new StringsCompleter("aggregation", "composition", "inheritance", "realization"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("save"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("load"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("changetype"),
                                new StringsCompleter("field", "method", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("display"),
                                new StringsCompleter("class", "all", "relationships"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("undo"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("redo"),
                                new NullCompleter()
                        ),
                        new ArgumentCompleter(
                                new StringsCompleter("exit"),
                                new NullCompleter()
                        ),
                        new ArgumentCompleter(
                                new StringsCompleter("help"),
                                new NullCompleter()
                        )
        );
    }

    public AggregateCompleter updateCompleter(DiagramModel model){
        Collection<Completer> completers = comp.getCompleters();
        completers = new ArrayList<>(completers);
        return new AggregateCompleter(completers);
    }

    public AggregateCompleter relationComplete(){
        AggregateCompleter completers;

        completers = new AggregateCompleter(
            new StringsCompleter("aggregation", "composition", "inheritance", "realization"),
            new NullCompleter()
        );
        return new AggregateCompleter(completers);
    }

}
