// Targeted by JavaCPP version 1.5-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.javacpp.opencv_ml;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_core.opencv_core.*;

import static org.bytedeco.javacpp.opencv_ml.opencv_ml.*;


/****************************************************************************************\
*                              Expectation - Maximization                                *
\****************************************************************************************/

/** \brief The class implements the Expectation Maximization algorithm.
<p>
@see \ref ml_intro_em
 */
@Namespace("cv::ml") @Properties(inherit = org.bytedeco.javacpp.opencv_ml.opencv_ml_presets.class)
public class EM extends StatModel {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public EM(Pointer p) { super(p); }

    /** Type of covariation matrices */
    /** enum cv::ml::EM::Types */
    public static final int
        /** A scaled identity matrix \f$\mu_k * I\f$. There is the only
        parameter \f$\mu_k\f$ to be estimated for each matrix. The option may be used in special cases,
        when the constraint is relevant, or as a first step in the optimization (for example in case
        when the data is preprocessed with PCA). The results of such preliminary estimation may be
        passed again to the optimization procedure, this time with
        covMatType=EM::COV_MAT_DIAGONAL. */
        COV_MAT_SPHERICAL = 0,
        /** A diagonal matrix with positive diagonal elements. The number of
        free parameters is d for each matrix. This is most commonly used option yielding good
        estimation results. */
        COV_MAT_DIAGONAL = 1,
        /** A symmetric positively defined matrix. The number of free
        parameters in each matrix is about \f$d^2/2\f$. It is not recommended to use this option, unless
        there is pretty accurate initial estimation of the parameters and/or a huge number of
        training samples. */
        COV_MAT_GENERIC = 2,
        COV_MAT_DEFAULT = COV_MAT_DIAGONAL;

    /** Default parameters */
    /** enum cv::ml::EM:: */
    public static final int DEFAULT_NCLUSTERS = 5, DEFAULT_MAX_ITERS = 100;

    /** The initial step */
    /** enum cv::ml::EM:: */
    public static final int START_E_STEP = 1, START_M_STEP = 2, START_AUTO_STEP = 0;

    /** The number of mixture components in the Gaussian mixture model.
    Default value of the parameter is EM::DEFAULT_NCLUSTERS=5. Some of %EM implementation could
    determine the optimal number of mixtures within a specified value range, but that is not the
    case in ML yet. */
    /** @see setClustersNumber */
    public native int getClustersNumber();
    /** \copybrief getClustersNumber @see getClustersNumber */
    public native void setClustersNumber(int val);

    /** Constraint on covariance matrices which defines type of matrices.
    See EM::Types. */
    /** @see setCovarianceMatrixType */
    public native int getCovarianceMatrixType();
    /** \copybrief getCovarianceMatrixType @see getCovarianceMatrixType */
    public native void setCovarianceMatrixType(int val);

    /** The termination criteria of the %EM algorithm.
    The %EM algorithm can be terminated by the number of iterations termCrit.maxCount (number of
    M-steps) or when relative change of likelihood logarithm is less than termCrit.epsilon. Default
    maximum number of iterations is EM::DEFAULT_MAX_ITERS=100. */
    /** @see setTermCriteria */
    public native @ByVal TermCriteria getTermCriteria();
    /** \copybrief getTermCriteria @see getTermCriteria */
    public native void setTermCriteria(@Const @ByRef TermCriteria val);

    /** \brief Returns weights of the mixtures
    <p>
    Returns vector with the number of elements equal to the number of mixtures.
     */
    public native @ByVal Mat getWeights();
    /** \brief Returns the cluster centers (means of the Gaussian mixture)
    <p>
    Returns matrix with the number of rows equal to the number of mixtures and number of columns
    equal to the space dimensionality.
     */
    public native @ByVal Mat getMeans();
    /** \brief Returns covariation matrices
    <p>
    Returns vector of covariation matrices. Number of matrices is the number of gaussian mixtures,
    each matrix is a square floating-point matrix NxN, where N is the space dimensionality.
     */
    public native void getCovs(@ByRef MatVector covs);

    /** \brief Returns posterior probabilities for the provided samples
    <p>
    @param samples The input samples, floating-point matrix
    @param results The optional output \f$ nSamples \times nClusters\f$ matrix of results. It contains
    posterior probabilities for each sample from the input
    @param flags This parameter will be ignored
     */
    public native @Override float predict( @ByVal Mat samples, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat results, int flags/*=0*/ );
    public native float predict( @ByVal Mat samples );
    public native @Override float predict( @ByVal UMat samples, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat results, int flags/*=0*/ );
    public native float predict( @ByVal UMat samples );
    public native @Override float predict( @ByVal GpuMat samples, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat results, int flags/*=0*/ );
    public native float predict( @ByVal GpuMat samples );

    /** \brief Returns a likelihood logarithm value and an index of the most probable mixture component
    for the given sample.
    <p>
    @param sample A sample for classification. It should be a one-channel matrix of
        \f$1 \times dims\f$ or \f$dims \times 1\f$ size.
    @param probs Optional output matrix that contains posterior probabilities of each component
        given the sample. It has \f$1 \times nclusters\f$ size and CV_64FC1 type.
    <p>
    The method returns a two-element double vector. Zero element is a likelihood logarithm value for
    the sample. First element is an index of the most probable mixture component for the given
    sample.
     */
    public native @ByVal Point2d predict2(@ByVal Mat sample, @ByVal Mat probs);
    public native @ByVal Point2d predict2(@ByVal UMat sample, @ByVal UMat probs);
    public native @ByVal Point2d predict2(@ByVal GpuMat sample, @ByVal GpuMat probs);

    /** \brief Estimate the Gaussian mixture parameters from a samples set.
    <p>
    This variation starts with Expectation step. Initial values of the model parameters will be
    estimated by the k-means algorithm.
    <p>
    Unlike many of the ML models, %EM is an unsupervised learning algorithm and it does not take
    responses (class labels or function values) as input. Instead, it computes the *Maximum
    Likelihood Estimate* of the Gaussian mixture parameters from an input sample set, stores all the
    parameters inside the structure: \f$p_{i,k}\f$ in probs, \f$a_k\f$ in means , \f$S_k\f$ in
    covs[k], \f$\pi_k\f$ in weights , and optionally computes the output "class label" for each
    sample: \f$\texttt{labels}_i=\texttt{arg max}_k(p_{i,k}), i=1..N\f$ (indices of the most
    probable mixture component for each sample).
    <p>
    The trained model can be used further for prediction, just like any other classifier. The
    trained model is similar to the NormalBayesClassifier.
    <p>
    @param samples Samples from which the Gaussian mixture model will be estimated. It should be a
        one-channel matrix, each row of which is a sample. If the matrix does not have CV_64F type
        it will be converted to the inner matrix of such type for the further computing.
    @param logLikelihoods The optional output matrix that contains a likelihood logarithm value for
        each sample. It has \f$nsamples \times 1\f$ size and CV_64FC1 type.
    @param labels The optional output "class label" for each sample:
        \f$\texttt{labels}_i=\texttt{arg max}_k(p_{i,k}), i=1..N\f$ (indices of the most probable
        mixture component for each sample). It has \f$nsamples \times 1\f$ size and CV_32SC1 type.
    @param probs The optional output matrix that contains posterior probabilities of each Gaussian
        mixture component given the each sample. It has \f$nsamples \times nclusters\f$ size and
        CV_64FC1 type.
     */
    public native @Cast("bool") boolean trainEM(@ByVal Mat samples,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat logLikelihoods,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat labels,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat probs);
    public native @Cast("bool") boolean trainEM(@ByVal Mat samples);
    public native @Cast("bool") boolean trainEM(@ByVal UMat samples,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat logLikelihoods,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat labels,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat probs);
    public native @Cast("bool") boolean trainEM(@ByVal UMat samples);
    public native @Cast("bool") boolean trainEM(@ByVal GpuMat samples,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat logLikelihoods,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat labels,
                             @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat probs);
    public native @Cast("bool") boolean trainEM(@ByVal GpuMat samples);

    /** \brief Estimate the Gaussian mixture parameters from a samples set.
    <p>
    This variation starts with Expectation step. You need to provide initial means \f$a_k\f$ of
    mixture components. Optionally you can pass initial weights \f$\pi_k\f$ and covariance matrices
    \f$S_k\f$ of mixture components.
    <p>
    @param samples Samples from which the Gaussian mixture model will be estimated. It should be a
        one-channel matrix, each row of which is a sample. If the matrix does not have CV_64F type
        it will be converted to the inner matrix of such type for the further computing.
    @param means0 Initial means \f$a_k\f$ of mixture components. It is a one-channel matrix of
        \f$nclusters \times dims\f$ size. If the matrix does not have CV_64F type it will be
        converted to the inner matrix of such type for the further computing.
    @param covs0 The vector of initial covariance matrices \f$S_k\f$ of mixture components. Each of
        covariance matrices is a one-channel matrix of \f$dims \times dims\f$ size. If the matrices
        do not have CV_64F type they will be converted to the inner matrices of such type for the
        further computing.
    @param weights0 Initial weights \f$\pi_k\f$ of mixture components. It should be a one-channel
        floating-point matrix with \f$1 \times nclusters\f$ or \f$nclusters \times 1\f$ size.
    @param logLikelihoods The optional output matrix that contains a likelihood logarithm value for
        each sample. It has \f$nsamples \times 1\f$ size and CV_64FC1 type.
    @param labels The optional output "class label" for each sample:
        \f$\texttt{labels}_i=\texttt{arg max}_k(p_{i,k}), i=1..N\f$ (indices of the most probable
        mixture component for each sample). It has \f$nsamples \times 1\f$ size and CV_32SC1 type.
    @param probs The optional output matrix that contains posterior probabilities of each Gaussian
        mixture component given the each sample. It has \f$nsamples \times nclusters\f$ size and
        CV_64FC1 type.
    */
    public native @Cast("bool") boolean trainE(@ByVal Mat samples, @ByVal Mat means0,
                            @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat covs0,
                            @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat weights0,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat logLikelihoods,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat labels,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat probs);
    public native @Cast("bool") boolean trainE(@ByVal Mat samples, @ByVal Mat means0);
    public native @Cast("bool") boolean trainE(@ByVal UMat samples, @ByVal UMat means0,
                            @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat covs0,
                            @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat weights0,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat logLikelihoods,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat labels,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat probs);
    public native @Cast("bool") boolean trainE(@ByVal UMat samples, @ByVal UMat means0);
    public native @Cast("bool") boolean trainE(@ByVal GpuMat samples, @ByVal GpuMat means0,
                            @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat covs0,
                            @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat weights0,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat logLikelihoods,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat labels,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat probs);
    public native @Cast("bool") boolean trainE(@ByVal GpuMat samples, @ByVal GpuMat means0);

    /** \brief Estimate the Gaussian mixture parameters from a samples set.
    <p>
    This variation starts with Maximization step. You need to provide initial probabilities
    \f$p_{i,k}\f$ to use this option.
    <p>
    @param samples Samples from which the Gaussian mixture model will be estimated. It should be a
        one-channel matrix, each row of which is a sample. If the matrix does not have CV_64F type
        it will be converted to the inner matrix of such type for the further computing.
    @param probs0
    @param logLikelihoods The optional output matrix that contains a likelihood logarithm value for
        each sample. It has \f$nsamples \times 1\f$ size and CV_64FC1 type.
    @param labels The optional output "class label" for each sample:
        \f$\texttt{labels}_i=\texttt{arg max}_k(p_{i,k}), i=1..N\f$ (indices of the most probable
        mixture component for each sample). It has \f$nsamples \times 1\f$ size and CV_32SC1 type.
    @param probs The optional output matrix that contains posterior probabilities of each Gaussian
        mixture component given the each sample. It has \f$nsamples \times nclusters\f$ size and
        CV_64FC1 type.
    */
    public native @Cast("bool") boolean trainM(@ByVal Mat samples, @ByVal Mat probs0,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat logLikelihoods,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat labels,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat probs);
    public native @Cast("bool") boolean trainM(@ByVal Mat samples, @ByVal Mat probs0);
    public native @Cast("bool") boolean trainM(@ByVal UMat samples, @ByVal UMat probs0,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat logLikelihoods,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat labels,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat probs);
    public native @Cast("bool") boolean trainM(@ByVal UMat samples, @ByVal UMat probs0);
    public native @Cast("bool") boolean trainM(@ByVal GpuMat samples, @ByVal GpuMat probs0,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat logLikelihoods,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat labels,
                            @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat probs);
    public native @Cast("bool") boolean trainM(@ByVal GpuMat samples, @ByVal GpuMat probs0);

    /** Creates empty %EM model.
    The model should be trained then using StatModel::train(traindata, flags) method. Alternatively, you
    can use one of the EM::train\* methods or load it from file using Algorithm::load\<EM\>(filename).
     */
    public static native @Ptr EM create();

    /** \brief Loads and creates a serialized EM from a file
     *
     * Use EM::save to serialize and store an EM to disk.
     * Load the EM from this file again, by calling this function with the path to the file.
     * Optionally specify the node for the file containing the classifier
     *
     * @param filepath path to serialized EM
     * @param nodeName name of node containing the classifier
     */
    public static native @Ptr EM load(@Str BytePointer filepath, @Str BytePointer nodeName/*=cv::String()*/);
    public static native @Ptr EM load(@Str BytePointer filepath);
    public static native @Ptr EM load(@Str String filepath, @Str String nodeName/*=cv::String()*/);
    public static native @Ptr EM load(@Str String filepath);
}