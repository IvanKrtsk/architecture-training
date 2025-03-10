package org.ikrotsyuk.bsuir.firstservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ikrotsyuk.bsuir.firstservice.dto.request.WriterRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.WriterResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.WriterEntity;
import org.ikrotsyuk.bsuir.firstservice.exception.JSONConverter;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.UserWithSameLoginFoundException;
import org.ikrotsyuk.bsuir.firstservice.mapper.WriterMapper;
import org.ikrotsyuk.bsuir.firstservice.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WriterService {
    private final WriterRepository writerRepository;
    private final WriterMapper writerMapper;
    private final JSONConverter jsonConverter;

    @Autowired
    public WriterService(WriterMapper writerMapper, WriterRepository writerRepository, JSONConverter jsonConverter){
        this.writerRepository = writerRepository;
        this.writerMapper = writerMapper;
        this.jsonConverter = jsonConverter;
    }

    public WriterResponseDTO addWriter(WriterRequestDTO writerRequestDTO) {
        Optional<WriterEntity> optionalWriterEntity = writerRepository.findByLogin(writerRequestDTO.getLogin());
        if(optionalWriterEntity.isEmpty())
            return writerMapper.toDTO(writerRepository.save(writerMapper.toEntity(writerRequestDTO)));
        else {
            try{
                throw new UserWithSameLoginFoundException(jsonConverter.convertObjectToJSON(writerRequestDTO));
            }catch (JsonProcessingException ex){
                return writerMapper.toDTO(optionalWriterEntity.get());
            }
        }
    }

    public WriterResponseDTO getWriterById(long id){
        Optional<WriterEntity> writerEntityOptional = writerRepository.findById(id);
        return writerEntityOptional.map(writerMapper::toDTO).orElse(null);
    }

    public List<WriterResponseDTO> getWriters(){
        List<WriterEntity> writerEntityList = writerRepository.findAll();
        if(writerEntityList.isEmpty())
            return Collections.emptyList();
        else
            return writerMapper.toDTOList(writerEntityList);
    }

    public HttpStatus deleteWriter(Long id){
        Optional<WriterEntity> optionalWriterEntity = writerRepository.findById(id);
        if(optionalWriterEntity.isPresent()) {
            writerRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus updateWriter(WriterResponseDTO writerResponseDTO){
        Optional<WriterEntity> optionalWriterEntity = writerRepository.findById(writerResponseDTO.getId());
        if(optionalWriterEntity.isPresent()){
            WriterEntity writerEntity = optionalWriterEntity.get();
            writerEntity.setLogin(writerResponseDTO.getLogin());
            writerEntity.setPassword(writerResponseDTO.getPassword());
            writerEntity.setFirstname(writerResponseDTO.getFirstname());
            writerEntity.setLastname(writerResponseDTO.getLastname());
            writerRepository.save(writerEntity);
            return HttpStatus.OK;
        }else
            return HttpStatus.BAD_REQUEST;
    }
}
