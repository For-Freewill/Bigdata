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

public class WordCountTotalBolt extends BaseRichBolt {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// collector：该bolt组件的收集器，用于把处理的数据发给下一个bolt组件
	private OutputCollector collector;
	
	private Map<String, Integer> result = new HashMap<>();

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;

	}

	@Override
	public void execute(Tuple tuple) {
		//取出数据
		String word = tuple.getStringByField("word");
		int count = tuple.getIntegerByField("count");
		if (result.containsKey(word)) {
			int total = result.get(word);
			result.put(word, count+total);
		}else {
			result.put(word, count);
		}
		//输出到屏幕
		System.out.println("统计结果是: "+result);
		
		//输出给下一个组件
		this.collector.emit(new Values(word,result.get(word)));

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		// TODO Auto-generated method stub
		declare.declare(new Fields("word", "total"));

	}

}
