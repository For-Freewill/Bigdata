package com.bigdata.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordCountTopology {
	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();
		
		//指定任务的spout组件
		builder.setSpout("mywordcountspout", new WordCountSpout());
		
		//指定任务的第一个bolt组件
		builder.setBolt("mywordcountsplit", new WordCountSplitBolt())
		       .shuffleGrouping("mywordcountspout");//随机分组
		
		//指定任务的第二个bolt组件
		builder.setBolt("mywordcounttotal", new WordCountTotalBolt())
		       .fieldsGrouping("mywordcountsplit", new Fields("word"));
		
//		//指定任务的第三个bolt组件，将结果写入Redis
//		builder.setBolt("mywordcountredisbolt", createRedisBolt())
//		       .shuffleGrouping("mywordcounttotal");
		
/*		//指定任务的第三个bolt组件，将结果写入HBase
		builder.setBolt("mywordcountredisbolt",new WordCountHBaseBolt())
		       .shuffleGrouping("mywordcounttotal");*/
		 
		
		//创建任务
		StormTopology job = builder.createTopology();
		
		Config conf = new Config();
		
		//任务有两种运行方式：1、本地模式   2、集群模式
		//1、本地模式
		LocalCluster localcluster = new LocalCluster();
		localcluster.submitTopology("MyWordCount", conf, job);
	}
	

}
