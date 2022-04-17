import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class CleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final int MISSING = 9999;

    @Override
    public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        if(words.length > 31){
            String borough = words[31];
            String year = words[29];
            if(borough.equals("MANHATTAN") && (year.equals("2018/19") || year.equals("2017/2018"))){
                String owner = words[5];
                String bldgcl = words[6];
                String fullVal = words[12];
                String postcode = words[19];
                //System.out.println(owner + "," + bldgcl + "," + fullVal + "," + postcode + "," + year + "," + borough);
                context.write(new Text(owner + "," + bldgcl + "," + fullVal + "," + postcode + "," + year + "," + borough), new IntWritable(1));
            }
        }
        
    }
}
