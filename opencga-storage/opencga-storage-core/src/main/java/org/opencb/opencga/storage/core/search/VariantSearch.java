package org.opencb.opencga.storage.core.search;

import org.apache.solr.client.solrj.beans.Field;

import java.util.*;

/**
 * Created by wasim on 09/11/16.
 */

public class VariantSearch {

    @Field
    private String id;

    @Field("dbSNP")
    private String dbSNP;

    @Field("type")
    private String type;

    @Field("chromosome")
    private String chromosome;

    @Field("start")
    private int start;

    @Field("end")
    private int end;

    @Field("gerp")
    private double gerp;

    @Field("caddRaw")
    private double caddRaw;

    @Field("caddScaled")
    private double caddScaled;

    @Field("phastCons")
    private double phastCons;

    @Field("phylop")
    private double phylop;

    @Field("sift")
    private double sift;

    @Field("polyphen")
    private double polyphen;

    @Field("studies")
    private String[] studies;

    @Field("genes")
    private Set<String> genes;

    public Map<String, String> getGeneToConsequenceType() {
        return geneToConsequenceType;
    }

    public VariantSearch setGeneToConsequenceType(Map<String, String> geneToConsequenceType) {
        this.geneToConsequenceType = geneToConsequenceType;
        return this;
    }

    @Field("genect_*")
    private Map<String, String> geneToConsequenceType;

    @Field("accessions")
    private Set<String> accessions;

    @Field("study_*")
    private Map<String, Float> populations;


    public VariantSearch() {
        this.accessions = new HashSet<>();
        this.genes = new HashSet<>();
        this.geneToConsequenceType = new HashMap<>();
        this.populations = new HashMap<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VariantSearch{");
        sb.append("id='").append(id).append('\'');
        sb.append(", dbSNP='").append(dbSNP).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", chromosome='").append(chromosome).append('\'');
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", gerp=").append(gerp);
        sb.append(", caddRaw=").append(caddRaw);
        sb.append(", caddScaled=").append(caddScaled);
        sb.append(", phastCons=").append(phastCons);
        sb.append(", phylop=").append(phylop);
        sb.append(", sift=").append(sift);
        sb.append(", polyphen=").append(polyphen);
        sb.append(", studies=").append(Arrays.toString(studies));
        sb.append(", genes=").append(genes);
        sb.append(", geneToConsequenceType=").append(geneToConsequenceType);
        sb.append(", accessions=").append(accessions);
        sb.append(", populations=").append(populations);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public VariantSearch setId(String id) {
        this.id = id;
        return this;
    }

    public String getDbSNP() {
        return dbSNP;
    }

    public VariantSearch setDbSNP(String dbSNP) {
        this.dbSNP = dbSNP;
        return this;
    }

    public String getType() {
        return type;
    }

    public VariantSearch setType(String type) {
        this.type = type;
        return this;
    }

    public String getChromosome() {
        return chromosome;
    }

    public VariantSearch setChromosome(String chromosome) {
        this.chromosome = chromosome;
        return this;
    }

    public int getStart() {
        return start;
    }

    public VariantSearch setStart(int start) {
        this.start = start;
        return this;
    }

    public int getEnd() {
        return end;
    }

    public VariantSearch setEnd(int end) {
        this.end = end;
        return this;
    }

    public double getGerp() {
        return gerp;
    }

    public VariantSearch setGerp(double gerp) {
        this.gerp = gerp;
        return this;
    }

    public double getCaddRaw() {
        return caddRaw;
    }

    public VariantSearch setCaddRaw(double caddRaw) {
        this.caddRaw = caddRaw;
        return this;
    }

    public double getCaddScaled() {
        return caddScaled;
    }

    public VariantSearch setCaddScaled(double caddScaled) {
        this.caddScaled = caddScaled;
        return this;
    }

    public double getPhastCons() {
        return phastCons;
    }

    public VariantSearch setPhastCons(double phastCons) {
        this.phastCons = phastCons;
        return this;
    }

    public double getPhylop() {
        return phylop;
    }

    public VariantSearch setPhylop(double phylop) {
        this.phylop = phylop;
        return this;
    }

    public double getSift() {
        return sift;
    }

    public VariantSearch setSift(double sift) {
        this.sift = sift;
        return this;
    }

    public double getPolyphen() {
        return polyphen;
    }

    public VariantSearch setPolyphen(double polyphen) {
        this.polyphen = polyphen;
        return this;
    }

    public String[] getStudies() {
        return studies;
    }

    public VariantSearch setStudies(String[] studies) {
        this.studies = studies;
        return this;
    }

    public Set<String> getGenes() {
        return genes;
    }

    public VariantSearch setGenes(Set<String> genes) {
        this.genes = genes;
        return this;
    }

//    public Map<String, List<String>> getGeneToConsequenceType() {
//        return geneToConsequenceType;
//    }
//
//    public VariantSearch setGeneToConsequenceType(Map<String, List<String>> geneToConsequenceType) {
//        this.geneToConsequenceType = geneToConsequenceType;
//        return this;
//    }

    public Set<String> getAccessions() {
        return accessions;
    }

    public VariantSearch setAccessions(Set<String> accessions) {
        this.accessions = accessions;
        return this;
    }

    public Map<String, Float> getPopulations() {
        return populations;
    }

    public VariantSearch setPopulations(Map<String, Float> populations) {
        this.populations = populations;
        return this;
    }

}

