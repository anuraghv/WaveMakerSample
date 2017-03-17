/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.wavemakersample.compose.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wavemakersample.compose.Todos;

/**
 * Service object for domain model class {@link Todos}.
 */
public interface TodosService {

    /**
     * Creates a new Todos. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Todos if any.
     *
     * @param todos Details of the Todos to be created; value cannot be null.
     * @return The newly created Todos.
     */
	Todos create(Todos todos);


	/**
	 * Returns Todos by given id if exists.
	 *
	 * @param todosId The id of the Todos to get; value cannot be null.
	 * @return Todos associated with the given todosId.
     * @throws EntityNotFoundException If no Todos is found.
	 */
	Todos getById(Integer todosId) throws EntityNotFoundException;

    /**
	 * Find and return the Todos by given id if exists, returns null otherwise.
	 *
	 * @param todosId The id of the Todos to get; value cannot be null.
	 * @return Todos associated with the given todosId.
	 */
	Todos findById(Integer todosId);


	/**
	 * Updates the details of an existing Todos. It replaces all fields of the existing Todos with the given todos.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Todos if any.
     *
	 * @param todos The details of the Todos to be updated; value cannot be null.
	 * @return The updated Todos.
	 * @throws EntityNotFoundException if no Todos is found with given input.
	 */
	Todos update(Todos todos) throws EntityNotFoundException;

    /**
	 * Deletes an existing Todos with the given id.
	 *
	 * @param todosId The id of the Todos to be deleted; value cannot be null.
	 * @return The deleted Todos.
	 * @throws EntityNotFoundException if no Todos found with the given id.
	 */
	Todos delete(Integer todosId) throws EntityNotFoundException;

	/**
	 * Find all Todos matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Todos.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Todos> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Todos matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Todos.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Todos> findAll(String query, Pageable pageable);

    /**
	 * Exports all Todos matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the Todos in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Todos.
	 */
	long count(String query);


}
