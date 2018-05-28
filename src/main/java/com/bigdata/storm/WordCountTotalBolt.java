package com.bigdata.storm;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

//第二个Bolt组件：单词的计数
public class WordCountTotalBolt extends BaseRichBolt{
	//使用Map集合存储结果
	private Map<String, Integer> result = new HashMap<>();
	
	//collector：该bolt组件的收集器，用于把处理的数据发给下一个bolt组件
	private OutputCollector collector;
	
	@Override
	public void execute(Tuple tuple) {
		//取出数据
		String word = tuple.getStringByField("word");
		int count = tuple.getIntegerByField("count");
		
		//求和
		if(result.containsKey(word)){
			//如果已经存在，累加
			int total = result.get(word);
			result.put(word, total+count);
		}else{
			//这是一个新单词
			result.put(word, count);
		}
		
		//输出到屏幕
		System.out.println("统计的结果是：" + result);
		
		//输出给下一个组件                                               单词           总频率
		this.collector.emit(new Values(word,result.get(word)));
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("word","total"));
	}
}
