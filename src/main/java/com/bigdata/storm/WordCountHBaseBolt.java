package com.bigdata.storm;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

//将结果写入HBase
public class WordCountHBaseBolt extends BaseRichBolt {

	//定义HBase的表的客户端
	private HTable table;
	
	@Override
	public void execute(Tuple tuple) {
		//取出数据
		String word = tuple.getStringByField("word");
		int total = tuple.getIntegerByField("total");
		
		//构造一个Put对象
		Put put = new Put(Bytes.toBytes(word));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("word"), Bytes.toBytes(word));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("total"), Bytes.toBytes(String.valueOf(total)));
		
		//插入数据
		try {
			table.put(put);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		//创建HBase的客户端
		//指定Zk
		Configuration conf =  new Configuration();
		conf.set("hbase.zookeeper.quorum", "10.30.30.146");
		
		try {
			table = new HTable(conf, "result");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub

	}

}