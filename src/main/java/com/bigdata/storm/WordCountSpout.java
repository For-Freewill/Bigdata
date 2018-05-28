package com.bigdata.storm;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

//spout组件
public class WordCountSpout extends BaseRichSpout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] data = { "I love Beijing", "I love China", "Beijing is the capital of China" };
	private SpoutOutputCollector collector;

	@Override
	public void nextTuple() {
		// 每隔3秒 采集一次数据
		Utils.sleep(3000);

		// storm引擎调用，用于处理采集的每条数据。

		// 随机取出数组里面的数据并不断产生。
		int random = (new Random()).nextInt(3);// 生成[0,3）的随机数
		String value = data[random];
		// 打印
		System.out.println("采集的数据是：" + value);
		// 发送给下一个组件
		this.collector.emit(new Values(value));

	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		// collector:当前spout组件的收集器，用于把数据采集的数据发给一下组件。
		// open方法中对collector初始化。
		this.collector = collector;

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		// 声明发给下一个组件的tuple的schema（结构）。
		declare.declare(new Fields("sentence"));
	}

}
