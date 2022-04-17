import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class avgByValZipMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    private static final int MISSING = 9999;

    @Override
    public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {
        
        String line = value.toString();
        String[] words = line.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        if(words.length == 6){
            String zip = words[3];
            double val = Double.parseDouble(words[2]);
            context.write(new Text(zip), new DoubleWritable(val));
        }
    }
}