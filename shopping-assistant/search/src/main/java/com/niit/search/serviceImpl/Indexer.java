package com.niit.search.serviceImpl;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

public class Indexer {

	private IndexWriter writer;

	public Indexer(String indexDir) throws Exception {

		Directory dir = FSDirectory.open(Paths.get(indexDir));

		Analyzer analyzer = new StandardAnalyzer();

		IndexWriterConfig con = new IndexWriterConfig(analyzer);

		writer = new IndexWriter(dir, con);

	}

	public void close() throws Exception {

		writer.close();
	}

	public int index(String dataDir) throws Exception {

		File[] file = new File(dataDir).listFiles();

		for (File files : file) {
			indexFile(files);
		}

		return writer.numDocs();

	}


	private void indexFile(File files) throws Exception {

		Document document = getDocument(files);

		writer.addDocument(document);

	}

	private Document getDocument(File files) throws Exception {

		Document doc = new Document();

		doc.add(new TextField("contents", new FileReader(files)));

		doc.add(new TextField("FileName", files.getName(), Field.Store.YES));

		doc.add(new TextField("fullPath", files.getCanonicalPath(), Field.Store.YES));

		return doc;
	}

	public static void main(String[] args) {

		String indexDir = "D:\\luceneDemo";

		String dataDir = "D:\\luceneDemo\\data";

		Indexer indexer = null;
		int numIndex = 0;

		long start = System.currentTimeMillis();

		try {
			indexer = new Indexer(indexDir);
			numIndex = indexer.index(dataDir);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				indexer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
