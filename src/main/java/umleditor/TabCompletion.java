package umleditor;

import org.jline.reader.Completer;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;

public class TabCompletion {
    
    private AggregateCompleter completer;

    public TabCompletion(){
        this.completer = new AggregateCompleter(
                            new ArgumentCompleter(
                                new StringsCompleter("add"),
                                new StringsCompleter("class", "field", "method", "parameter"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("delete"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("rename"),
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
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("display"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("undo"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("redo"),
                                new NullCompleter()
                        )
        );
    }

    
}
